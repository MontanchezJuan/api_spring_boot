package com.mssecurity.mssecurity.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.mssecurity.mssecurity.Services.JWTService;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler)

            throws Exception {
        boolean no_success = false;
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null &&
                authorizationHeader.startsWith(BEARER_PREFIX)) {

            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            // Verifica el token aquí, por ejemplo, con un servicio de autenticación
            // Si el token es válido, puedes permitir que la solicitud continúe
            // Si no es válido, puedes rechazar la solicitud o realizar otra acción
            // apropiada.
            // Por simplicidad, aquí solo se muestra cómo imprimir el token.
            System.out.println("Bearer Token: " + token);

            return jwtService.validateToken(token);
        }

        // Devuelve true para permitir que la solicitud continúe o false para bloquearla
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return no_success;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler,

            ModelAndView modelAndView) throws Exception {
        // Lógica a ejecutar después de que se haya manejado la solicitud por el
        // controlador
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,

            Exception ex) throws Exception {
        // Lógica a ejecutar después de completar la solicitud, incluso después de la
        // renderización de la vista
    }
}
