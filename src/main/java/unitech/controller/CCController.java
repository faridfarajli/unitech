package unitech.controller;

import org.springframework.web.bind.annotation.*;
import unitech.dto.CCDto;
import unitech.model.CreditCard;
import unitech.service.CCService;

import java.util.List;

@RestController
@RequestMapping()
public class CCController {
    private final CCService ccService;

    public CCController(CCService ccService) {
        this.ccService = ccService;
    }

    @PostMapping("/add/cc")
    public Object addCC(@RequestBody CCDto ccDto){
     return  ccService.addCC(ccDto);

    }
    @PostMapping("/update/{id}")
    public Object updateAccountActiveStatus(@PathVariable Long id){
        return ccService.updateAccountActiveStatus(id);

    }

    @PostMapping("/transfer")
    public String transferFunds(@RequestParam String senderAccountNumber,
                                @RequestParam String recipientAccountNumber,
                                @RequestParam double amount) {
        ccService.transferBetweenAccounts(senderAccountNumber, recipientAccountNumber, amount);
        return "Transfer successful.";
    }

    @GetMapping("/find/all")
    public List<CreditCard> getAllActiveCC(){
       return ccService.getAllActiveCC();
    }

}
