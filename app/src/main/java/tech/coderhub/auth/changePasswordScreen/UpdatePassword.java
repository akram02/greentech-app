package tech.coderhub.auth.changePasswordScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePassword {
    @SerializedName("currentPassword")
    @Expose
    String currentPassword;

    @SerializedName("newPassword")
    @Expose
    String newPassword;

    public UpdatePassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
