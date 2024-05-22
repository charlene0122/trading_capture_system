package com.beaconfire.project.trading.ordermanagement.request.user;

import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchByEmailRequest {
    @NotNull(message = "Email cannot be null")
    private String searchContent;

    private Integer pageSize;

    private Integer pageNumber;
}
