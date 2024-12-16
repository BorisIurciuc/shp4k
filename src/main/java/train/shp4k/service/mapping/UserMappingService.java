package train.shp4k.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMappingService {

  @Mapping(target = "id", ignore = true)
  User mapDtoToEntity(UserDto dto);
  UserDto mapEntityToDto(User entity);

}
