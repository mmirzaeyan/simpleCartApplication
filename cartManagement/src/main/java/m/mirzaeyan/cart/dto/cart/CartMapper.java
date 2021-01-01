package m.mirzaeyan.cart.dto.cart;

import m.mirzaeyan.cart.domain.Cart;
import m.mirzaeyan.cart.dto.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDto, Cart> {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Override
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.userName")
    CartDto toDto(Cart entity);


    @Override
    @Mapping(target = "user.id", source = "userId")
    Cart toEntity(CartDto dto);
}
