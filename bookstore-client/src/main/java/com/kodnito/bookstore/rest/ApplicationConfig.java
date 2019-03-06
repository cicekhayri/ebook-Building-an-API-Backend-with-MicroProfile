package com.kodnito.bookstore.rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/")
@DeclareRoles({"mysimplerole", "ADMIN"})
public class ApplicationConfig extends Application {

}
