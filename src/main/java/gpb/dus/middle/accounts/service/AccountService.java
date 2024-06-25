package gpb.dus.middle.accounts.service;

import gpb.dus.middle.api.generated.model.CreateAccountRequestV2Api;

public interface AccountService {

    void createUserAccount(String tgUserId, CreateAccountRequestV2Api createAccountRequestV2Api);
}
