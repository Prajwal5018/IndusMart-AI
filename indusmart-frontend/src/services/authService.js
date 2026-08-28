import axiosConfig from "../api/axiosConfig";

const login = async (data) => {

    const response = await axiosConfig.post(

        "/api/auth/login",

        data

    );

    return response.data;

};

const register = async (data) => {

    const response = await axiosConfig.post(

        "/api/auth/register",

        data

    );

    return response.data;

};

const logout = () => {

    localStorage.removeItem("token");

};

const authService = {

    login,

    register,

    logout,

};

export default authService;