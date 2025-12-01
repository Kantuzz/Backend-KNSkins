package com.csgoskins.catalogservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCreateDto {

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    private String clienteNombre;

   @NotBlank(message = "El correo del cliente no puede estar vacío")
    @Email(message = "El formato del correo no es válido")
    @Pattern(
        regexp = "^(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "El correo no puede contener '..' ni caracteres no permitidos"
    )
    private String clienteEmail;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    private String direccionEnvio;

    private Integer total;

    private List<OrdenItemDto> items;
}
