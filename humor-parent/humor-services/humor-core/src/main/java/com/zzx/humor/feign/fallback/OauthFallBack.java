package com.zzx.humor.feign.fallback;

import com.zzx.humor.constants.ServerConstant;
import com.zzx.humor.feign.OauthClient;
import com.zzx.humor.result.R;
import com.zzx.humor.result.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class OauthFallBack implements OauthClient {

    @Override
    public R exitFormer(String clientId, String account) {
        log.error(ServerConstant.CALL_OAUTH_TIMEOUT);
        return R.failed();
    }

    @Override
    public HashMap<String, String> getToken(String username, String password, String client_id, String client_secret, String grant_type, String scope) {
        return failed();
    }

    @Override
    public R logout(String token) {
        return R.other(RE.TIMEOUT);
    }

    @Override
    public HashMap<String, String> refreshToken(String client_id, String client_secret, String grant_type, String refresh_token) {
        return failed();
    }
    private  HashMap<String,String> failed(){
        log.error(ServerConstant.CALL_OAUTH_TIMEOUT);
        RE re =RE.TIMEOUT;
        HashMap hashMap = new HashMap();
        hashMap.put("code",re.getCode());
        hashMap.put("msg",re.getMsg());
        return hashMap;
    }
}
