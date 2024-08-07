package com.api.newsletter.syonet.dtos;

import java.util.Optional;

public record ClienteDTO(String nome, String email, Optional<String> dtNascimento) {
}
