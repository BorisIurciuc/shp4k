package train.shp4k.service.mapping;

import org.mapstruct.*;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.User;

@Mapper(componentModel = "spring",
    uses = {CartItemMappingService.class},
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CartMappingService {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "cartItems", source = "cartItems")
  @Mapping(target = "user", source = "user", qualifiedByName = "mapUserDtoToUser")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Cart mapDtoToEntity(CartDto dto);

  @Mapping(target = "cartItems", source = "cartItems")
  @Mapping(target = "user", source = "user", qualifiedByName = "mapUserToUserDto")
  CartDto mapEntityToDto(Cart entity);

  @Named("mapUserDtoToUser")
  default User mapUserDtoToUser(UserDto userDto) {
    if (userDto == null) {
      return null;
    }
    User user = new User();
    user.setId(userDto.getId());
    // Map other necessary fields
    return user;
  }

  @Named("mapUserToUserDto")
  default UserDto mapUserToUserDto(User user) {
    if (user == null) {
      return null;
    }
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    // Map other necessary fields
    return userDto;
  }
}
//  @Mapping(target = "id", ignore = true)
//  //@Mapping(target = "createdAt", ignore = true)
//  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//  void updateEntityFromDto(CartDto dto, @MappingTarget Cart entity);




//  /**
//   * Updates an existing Cart entity with data from a DTO.
//   * Ignores null values to prevent unintended overwrites.
//   *
//   * @param dto the CartDto containing updated data
//   * @param entity the existing Cart entity to update
//   * @return the updated Cart entity
//   */
//  @Mapping(target = "id", ignore = true)
//  @Mapping(target = "createdAt", ignore = true)
//  @BeanMapping(
//      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//      resultType = Cart.class
//  )
//  Cart updateEntityFromDto(CartDto dto, @MappingTarget Cart entity);
//
//  /**
//   * Creates a new CartDto with null values for all fields.
//   * Useful for initializing new DTOs.
//   *
//   * @return an empty CartDto
//   */
//  @Named("emptyCart")
//  default CartDto createEmptyCartDto() {
//    return new CartDto();
//  }
//
//  /**
//   * Validates if the source Cart entity is null.
//   * Used internally by MapStruct.
//   *
//   * @param cart the Cart entity to check
//   * @return true if the cart is not null
//   */
//  @Named("isNotNull")
//  default boolean isNotNull(Cart cart) {
//    return cart != null;
//  }


