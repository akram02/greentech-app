package tech.coderhub.auth.forgotPasswordScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassword {

    @SerializedName("username_or_email")
    @Expose
    String usernameOrEmail;

    @SerializedName("device_id")
    @Expose
    String deviceId;

    @SerializedName("id")
    @Expose
    Integer id;

    public ForgotPassword(String usernameOrEmail, String deviceId) {
        this.usernameOrEmail = usernameOrEmail;
        this.deviceId = deviceId;
    }
}
