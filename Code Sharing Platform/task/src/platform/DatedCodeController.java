package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class DatedCodeController {

    @Autowired
    private DateManager dateManager;

    @GetMapping("/code/latest")
    public String getHtml(final Model model) {
        model.addAttribute("codelist", dateManager.getDatedCode(dateManager.getLatest(), 10));
        return "codelist";
    }

    @GetMapping("/code/{id}")
    public String getHtml2(final @PathVariable UUID id, final Model model) {
        final DatedCode datedCode = dateManager.getDatedCodeFromUUID((id));
        if (datedCode == null) {
            throwNotFound();
        }
        datedCode.updateRestrictions();
        if (datedCode.isExpired()) {
            dateManager.deleteDatedCode(datedCode);
//            throwNotFound();
        }
        model.addAttribute("snippet", datedCode);
        model.addAttribute("date", datedCode.getDate());
        model.addAttribute("code", datedCode.getCode());
        model.addAttribute("time", datedCode.getTime());
        model.addAttribute("views", datedCode.getViews());
        model.addAttribute("viewsRestricted", datedCode.isViewsRestricted());
        model.addAttribute("timeRestricted", datedCode.isTimeRestricted());
        dateManager.saveDatedCode(datedCode);
        return "viewsnippet";
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public DatedCode getJson(@PathVariable final UUID id) {
        final DatedCode datedCode = dateManager.getDatedCodeFromUUID(id);
        if (datedCode == null) {
            throwNotFound();
        }
        datedCode.updateRestrictions();
        if (datedCode.isExpired()) {
            dateManager.deleteDatedCode(datedCode);
            throwNotFound();
        }
        dateManager.saveDatedCode(datedCode);
        return datedCode;
    }

    @GetMapping("/code/new")
    public String getNewCodeHtml() {
        return "new";
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public String newCode(@RequestBody DatedCode code) {
        code.setDateTime(LocalDateTime.now());
        final UUID uuid = UUID.randomUUID();
        code.setUuid(uuid);
        dateManager.addDatedCode(code);
        dateManager.saveDatedCode(code);
        return "{" +
                "\"id\": \"" + uuid + "\"" +
                "}";
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<DatedCode> getLatest() {
        return dateManager.getDatedCode(dateManager.getLatest(), 10);
    }

    private void throwNotFound() {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "DatedCode not found"
        );
    }
}
