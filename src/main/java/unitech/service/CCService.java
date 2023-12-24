package unitech.service;

import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;

import org.springframework.stereotype.Service;
import unitech.dto.CCDto;
import unitech.model.CreditCard;
import unitech.repo.CCRepository;

import java.util.List;

import static unitech.util.ErrorMessage.*;

@Service
public class CCService {

    private final CCRepository ccRepository;

    public CCService(CCRepository ccRepository) {
        this.ccRepository = ccRepository;
    }

    public Object addCC(@NotNull CCDto ccDto) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCcNumber(ccDto.ccNumber());
        creditCard.setCcDate(ccDto.ccDate());
        creditCard.setBalance(ccDto.balance());
        creditCard.setCVV(ccDto.CVV());
        creditCard.setActiveAccount(ccDto.activeAccount());
        creditCard.setUser(ccDto.user());

        if (creditCard.getCcNumber().length() != 12) {
            return WRONG_CC_NUMBER;
        }
        if (creditCard.getCcDate().length() != 4) {
            return WRONG_CC_DATE;
        }
        if (creditCard.getCVV().length() != 3) {
            return WRONG_CCV_NUMBER;
        }
        if (ccRepository.existsByCcNumber(creditCard.getCcNumber())) {
            return TAKEN_CC_NUMBER;
        }
        ccRepository.save(creditCard);
        return creditCard;


    }




    public Object updateAccountActiveStatus(Long id) {
        CreditCard creditCard = ccRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Forum not found"));
        creditCard.setActiveAccount(false);
        ccRepository.save(creditCard);
        return creditCard;
    }


    public List<CreditCard> getAllActiveCC() {
        return ccRepository.getAllActiveAccount();

    }

    public CreditCard getAccountByNumber(String accountNumber) {
        return ccRepository.findById(Long.valueOf(accountNumber)).orElse(null);
    }

    public void transferBetweenAccounts(String senderAccountNumber, String recipientAccountNumber, Double amount) {
        CreditCard senderAccount = getAccountByNumber(senderAccountNumber);
        CreditCard recipientAccount = getAccountByNumber(recipientAccountNumber);
        if (senderAccount == null || recipientAccount == null) {
            throw new IllegalArgumentException("One or both accounts do not exist.");
        }

        if (senderAccount == recipientAccount) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }

        if (!senderAccount.getActiveAccount() || !recipientAccount.getActiveAccount()) {
            throw new IllegalArgumentException("One of the accounts is inactive.");
        }

        if (senderAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);

        ccRepository.save(senderAccount);
        ccRepository.save(recipientAccount);
    }

}


//    public static boolean isValidCreditCardNumber(String cardNumber) {
//        int sum = 0;
//        boolean alternate = false;
//        for (int i = cardNumber.length() - 1; i >= 0; i--) {
//            int digit = Character.getNumericValue(cardNumber.charAt(i));
//            if (alternate) {
//                digit *= 2;
//                digit = (digit > 9) ? (digit - 9) : digit;
//            }
//            sum += digit;
//            alternate = !alternate;
//        }
//        return (sum % 10 == 0);
//    }



