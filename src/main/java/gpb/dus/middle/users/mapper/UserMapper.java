package gpb.dus.middle.users.mapper;

import gpb.dus.middle.api.generated.model.CreateUserRequestV2Api;
import gpb.dus.middle.users.model.CreateUserRequestV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserRequestV2 toModel(CreateUserRequestV2Api createUserRequestV2Api);

}
