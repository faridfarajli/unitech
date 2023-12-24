package unitech.dto;

import jakarta.validation.constraints.NotNull;
import unitech.model.User;

public record CCDto(
         Long id,
         @NotNull
         String ccNumber,
         @NotNull
         String ccDate,
         @NotNull
         String CVV,
         @NotNull
         Boolean activeAccount,
         @NotNull
         Double balance,
         @NotNull
         User user
) {
}
