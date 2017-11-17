package com.rramos.loginservice.services;

import com.rramos.loginservice.models.Denuncia;
import com.rramos.loginservice.models.ResponseMessage;
import com.rramos.loginservice.models.Usuario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by RODRIGO on 13/11/2017.
 */

public interface ApiService {
    String API_BASE_URL = "https://denuncias-api-rodrigoramosq.c9users.io/";

    @GET("api/v1/denuncias")
    Call<List<Denuncia>> getDenuncias();

    @FormUrlEncoded
    @POST("/api/v1/register_user")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                         @Field("email") String email,
                                         @Field("password") String password);
    @Multipart
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> createDenunciaWithImage(
            @Part("titulo") RequestBody titulo,
            @Part("comentario") RequestBody comentario,
            @Part("latitud") RequestBody latitud,
            @Part("longitud") RequestBody longitud,
            @Part("usuarios_id") RequestBody usuarios_id,
            @Part MultipartBody.Part imagen
    );

    @GET("api/v1/show_user/{id}")
    Call<Usuario> getUsuario(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<Usuario> loginUsuario(@Field("username") String username,
                               @Field("password") String password);
    @DELETE("/api/v1/denuncias/{id}")
    Call<ResponseMessage> destroyDenuncia(@Path("id") Integer id);

}
