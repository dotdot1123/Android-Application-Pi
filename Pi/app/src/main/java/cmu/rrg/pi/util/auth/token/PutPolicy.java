package cmu.rrg.pi.util.auth.token;

/**
 * Created by Kid7st on 5/3/16.
 */
import org.json.JSONException;
import org.json.JSONStringer;

import cmu.rrg.pi.exception.AuthException;

public class PutPolicy {
    /** 必选。可以是 bucketName 或者 bucketName:key */
    public String scope;
    /** 可选 */
    public String callbackUrl;
    /** 可选 */
    public String callbackBody;
    /** 可选 */
    public String returnUrl;
    /** 可选 */
    public String returnBody;
    /** 可选 */
    public String asyncOps;
    /** 可选 */
    public String endUser;
    /** 可选 */
    public long expires;

    public PutPolicy(String scope) {
        this.scope = scope;
    }

    private String marshal() throws JSONException {
        JSONStringer stringer = new JSONStringer();
        stringer.object();
        stringer.key("scope").value(this.scope);
        if (this.callbackUrl != null && this.callbackUrl.length() > 0) {
            stringer.key("callbackUrl").value(this.callbackUrl);
        }
        if (this.returnUrl != null && this.returnUrl.length() > 0) {
            stringer.key("returnUrl").value(this.returnUrl);
        }
        if (this.asyncOps != null && this.asyncOps.length() > 0) {
            stringer.key("asyncOps").value(this.asyncOps);
        }

        if (this.returnBody != null && this.returnBody.length() > 0) {
            stringer.key("returnBody").value(this.returnBody);
        }

        stringer.key("deadline").value(this.expires);
        stringer.endObject();

        return stringer.toString();
    }

    /**
     * makes an upload token.
     *
     * @param mac
     * @return
     * @throws AuthException
     * @throws JSONException
     */

    public String token(Mac mac) throws AuthException, JSONException {
        if (this.expires == 0) {
            this.expires = 3600; // 3600s, default.
        }
        this.expires = System.currentTimeMillis() / 1000 + expires;
        byte[] data = this.marshal().getBytes();
        return DigestAuth.signWithData(mac, data);
    }
}
