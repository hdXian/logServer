package hdxian.loggerProg.Controller;

import hdxian.loggerProg.Service.LogAdminService;
import hdxian.loggerProg.domain.ChangePwForm;
import hdxian.loggerProg.domain.LogAdmin;
import hdxian.loggerProg.domain.LogAdminForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
public class loginController {

    private final LogAdminService service;

    @Autowired
    public loginController(LogAdminService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login/loginPage";
    }

    @PostMapping("/login")
    public String login(LogAdminForm form, HttpServletRequest request) {
        // System.out.println(form.getId());
        Optional<LogAdmin> res = service.findAdmin(form.getId());

        if(res.isEmpty()) { // 해당 계정이 없을 경우
            return "redirect:/";
        }
        else { // 해당 계정이 있을 경우
            try {
                LogAdmin admin = res.get();
                String pw = tomd5(form.getPassword());

                // 로그인 성공
                if (admin.getPassword().equals(pw)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("adminInfo", admin);
                    return "redirect:/home";
                }
                // 로그인 실패
                else
                    return "redirect:/";
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/";
            }
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login/changePw")
    public String getChangePwPage() {
        return "login/changePwPage";
    }

    @PostMapping("/login/changePw")
    public String changePw(ChangePwForm form) {

        Optional<LogAdmin> res = service.findAdmin(form.getId());

        // 해당 계정이 없을 경우
        if(res.isEmpty()) {
            return "redirect:/login/changePw";
        }

        // 해당 계정이 있을 경우
        LogAdmin admin = res.get();
        try {
            String oldPw = tomd5(form.getOldPw());
            // 입력한 비밀번호가 맞을 경우 새 비밀번호로 변경
            if (admin.getPassword().equals(oldPw)) {
                String newPw = tomd5(form.getNewPw());
                admin.setPassword(newPw);
                Optional<LogAdmin> changed = service.changePw(admin);

                System.out.println(changed.get().getId());
                System.out.println(changed.get().getPassword());
                return "redirect:/";
            }
            else // 아니면 비밀번호 변경 화면으로 다시 돌아옴
                return "redirect:/login/changePw";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }


    private static String tomd5(String pw) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes(StandardCharsets.UTF_8));
        byte[] byteData = md.digest();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


}
