package tech.coderhub.greenapp.di.network

import retrofit2.http.*
import tech.coderhub.auth.changePasswordScreen.UpdatePassword
import tech.coderhub.auth.forgotPasswordScreen.ForgotPassword
import tech.coderhub.auth.forgotPasswordScreen.PasswordChangeResult
import tech.coderhub.auth.forgotPasswordScreen.PasswordSubmit
import tech.coderhub.auth.loginScreen.Token
import tech.coderhub.auth.loginScreen.User
import tech.coderhub.auth.loginScreen.UserSubmit
import tech.coderhub.auth.profileDetailsScreen.Profile
import tech.coderhub.auth.profileEditScreen.UserDTO
import tech.coderhub.greenapp.ui.product.productList.Product

/*import tech.coderhub.badhan.model.Donor
import tech.coderhub.badhan.model.Programme
import tech.coderhub.badhan.model.Requisition
import tech.coderhub.badhan.ui.donor.DonorUrl
import tech.coderhub.badhan.ui.programme.ProgrammeUrl
import tech.coderhub.badhan.ui.requisition.RequisitionUrl
import tech.coderhub.badhan.ui.user.UserUrl*/

interface RestApi {
    @POST("/api/authenticate")
    suspend fun getUserToken(@Body userSubmit: UserSubmit): Token

    @POST("/api/register-account-from-mobile")
    suspend fun getUserTokenByRegister(@Body userSubmit: UserSubmit): Token

    @GET("/api/account")
    suspend fun getUserDetails(): User

    @GET("/api/profile/")
    suspend fun getProfile(): Profile

    @POST("/api/forgot")
    suspend fun sendPinToEmail(@Body email: ForgotPassword): ForgotPassword

    @POST("/api/forgot/change-password")
    suspend fun changePassword(@Body passwordSubmit: PasswordSubmit): PasswordChangeResult

    @PUT("/api/update-picture-and-name")
    suspend fun updateUser(@Body userDTO: UserDTO): UserDTO

    @PUT("/api/update-password")
    suspend fun updatePassword(@Body updatePassword: UpdatePassword): Unit

    @GET("/api/products")
    suspend fun getAllProduct(): List<Product>

/*
    @GET(ProgrammeUrl.ALL_PROGRAMME)
    suspend fun getAllProgramme(): List<Programme>

    @DELETE(ProgrammeUrl.DELETE_PROGRAMME)
    suspend fun deleteProgramme(@Path("id") id: String): Unit

    @POST(ProgrammeUrl.SAVE_PROGRAMME)
    suspend fun saveProgramme(@Body programme: Programme): Programme

    @POST(DonorUrl.SAVE_DONOR)
    suspend fun saveDonor(@Body donor: Donor): Donor

    @GET(DonorUrl.GET_DONOR)
    suspend fun getDonor(@Path("id") id: String): Donor

    @PUT(DonorUrl.UPDATE_DONOR)
    suspend fun updateDonor(@Body donor: Donor): Donor

    @GET(DonorUrl.THIS_MONTH_DONOR)
    suspend fun getDonorOfThisMonth(): List<Donor>

    @GET(DonorUrl.SEARCH_DONOR)
    suspend fun searchDonorByKeyValue(@QueryMap query: Map<String, String>): List<Donor>

    @DELETE(DonorUrl.DELETE_DONOR)
    suspend fun deleteDonor(@Path("id") id: String): Unit

    @POST(RequisitionUrl.SAVE_REQUISITION)
    suspend fun saveRequisition(@Body requisition: Requisition): Requisition

    @GET(RequisitionUrl.ALL_REQUISITION)
    suspend fun getAllRequisition(): List<Requisition>

    @GET(UserUrl.ALL_USER)
    suspend fun getAllUser(): List<com.akramkhan.badhan.model.User>

    @GET(UserUrl.USER)
    suspend fun getUser(@Path("login") login: String): com.akramkhan.badhan.model.User

    @PUT(UserUrl.UPDATE_USER)
    suspend fun updateUser(@Body user: com.akramkhan.badhan.model.User): com.akramkhan.badhan.model.User*/
    /*@GET("/api/order-item/{id}")
    Single<OrderItemList> getPriceCollectionList(@Path("id") String orderId);

    @GET("/api/complete-order")
    Single<OrderList> getCompleteOrderList();

    @PUT("/api/order-item")
    Single<OrderItemList> updateRate(@Body OrderItem orderItem);

    @POST("/api/order-item/{id}")
    Single<OrderItemList> finishPriceCollection(@Path("id") String orderId);


    @GET("/api/confirmed-order-list")
    Single<OrderList> getConfirmedOrderList();

    @GET("/api/confirmed-item-list/{id}")
    Single<OrderItemList> getConfirmedItemList(@Path("id") String orderId);

    @POST("/api/finish-picked/{id}")
    Single<OrderItemList> finishPicked(@Path("id") String orderId);

    @PUT("/api/update-picked-status")
    Single<OrderItemList> updatePickedStatus(@Body OrderItem orderItem);

    @GET("/api/nearby-merchants/{id}")
    Single<MerchantList> getNearbyMerchants(@Path("id") String orderId);

    @GET("/api/picked-order-list")
    Single<OrderList> getPickedOrderList();

    @GET("/api/picked-item-list/{id}")
    Single<OrderItemList> getPickedItemList(@Path("id") String orderId);

    @POST("/api/finish-delivery/{id}")
    Single<OrderItemList> finishDelivery(@Path("id") String orderId);

    @PUT("/api/update-delivery-status")
    Single<OrderItemList> updateDeliveryStatus(@Body OrderItem orderItem);*/
}