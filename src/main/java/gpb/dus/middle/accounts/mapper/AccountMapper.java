package gpb.dus.middle.accounts.mapper;

import gpb.dus.middle.accounts.model.CreateUserAccountV2;
import gpb.dus.middle.api.generated.model.CreateAccountRequestV2Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreateUserAccountV2 toModel(CreateAccountRequestV2Api createAccountRequestV2Api);
}



