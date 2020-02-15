package tech.coderhub.auth.profileEditScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("user_image")
    @Expose
    String userImage;

    @SerializedName("full_name")
    @Expose
    String fullName;

    @SerializedName("status")
    @Expose
    String status;

    public UserDTO(String userImage, String fullName) {
        this.userImage = userImage;
        this.fullName = fullName;
    }
}
