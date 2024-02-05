package com.cgi.nl.mappers;

import com.cgi.nl.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper
{
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
    Recipe mapRecipeResponse(RecipieDTO recipieDTO);
    Recipe searchDtoEntity(RecipieSearchDTO recipieSearchDTO);
    @Mapping(target = "id", source = "Id")
    Recipe mapRecipe(RecipieDTO recipieDTO, Long Id);


    default List<ResponseDTO> entityToResponse(List<Recipe> recipes) {
        return recipes.stream().map(this::toGetResponseBody).toList();
    }

    @Mapping(source = "id", target = "receipeId")
    ResponseDTO toGetResponseBody(Recipe recipe);

}
