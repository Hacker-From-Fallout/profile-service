package com.ignat.chernyshov.profile.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AboutMyselfDto(
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(max = 500, message = "Количество символов не должна превышать 500")
    String aboutMyself
) {}
