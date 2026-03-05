package br.com.ehc.controllers.docs;

import br.com.ehc.data.dto.v1.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDocs {
    @Operation(
            summary = "Lista todas as pessoas",
            description = "Listagem de pessoas",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                            )
                    }
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    List<PersonDTO> getAll();

    @Operation(
            summary = "Pesquisa uma pessoa por ID",
            description = "Pesquisar pessoa específica por Id.",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class)
                    )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    PersonDTO findById(@PathVariable("id") Long id);

    @Operation(
            summary = "Inclui uma pessoa",
            description = "Cadastro uma pessoa",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "201",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class)
                    )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    PersonDTO create(@RequestBody PersonDTO personDTO);

    @Operation(
            summary = "Altera uma pessoa.",
            description = "Edita uma pessoa",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class)
                    )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    PersonDTO update(@RequestBody PersonDTO personDTO);

    @Operation(
            summary = "Exclui uma pessoa por ID",
            description = "Delete uma pessoa por Id.",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "204",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class)
                    )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    }
    )
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
