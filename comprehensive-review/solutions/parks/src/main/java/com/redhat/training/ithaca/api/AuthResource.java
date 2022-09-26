package com.redhat.training.ithaca.api;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.Claims;

import com.redhat.training.ithaca.services.JwtService;
import com.redhat.training.ithaca.services.UserService;

import io.smallrye.jwt.build.Jwt;


class UserCredentials {
	public String username;
	public String password;
}


@Path( "/auth" )
@RequestScoped
public class AuthResource {

    @Inject
    UserService userService;

    @Inject
    JwtService jwtService;

    @POST
    @Path("/login")
    public Response login(UserCredentials credentials) {
        if (userService.authenticate(credentials.username, credentials.password)) {
            var token = jwtService.createTokenForUser(credentials.username);
            return Response.ok(token).build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }
}
