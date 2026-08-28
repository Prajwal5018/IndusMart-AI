import { createContext, useContext, useEffect, useState } from "react";
import { decodeToken, isTokenExpired } from "../utils/jwtUtils";

const AuthContext = createContext();

export function AuthProvider({ children }) {

    const [user, setUser] = useState(null);

    const [token, setToken] = useState(

        localStorage.getItem("token")

    );

    useEffect(() => {

        if (!token) {

            setUser(null);

            return;

        }

        if (isTokenExpired(token)) {

            logout();

            return;

        }

        const decoded = decodeToken(token);

        setUser(decoded);

    }, [token]);

    const login = (jwt) => {

        localStorage.setItem(

            "token",

            jwt

        );

        setToken(jwt);

    };

    const logout = () => {

        localStorage.removeItem("token");

        setToken(null);

        setUser(null);

    };

    return (

        <AuthContext.Provider

            value={{

                token,

                user,

                login,

                logout,

                isAuthenticated: !!token

            }}

        >

            {children}

        </AuthContext.Provider>

    );

}

export function useAuth() {

    return useContext(AuthContext);

}