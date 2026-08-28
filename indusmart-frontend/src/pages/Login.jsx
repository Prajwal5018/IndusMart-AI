import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../services/authService";

function Login() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        setLoading(true);

        try {

            const response = await authService.login(formData);

            localStorage.setItem(

                "token",

                response.token

            );

            alert("Login Successful");

            navigate("/buyer/dashboard");

        }

        catch (error) {

            alert(

                error.response?.data?.message ||

                "Invalid Email or Password"

            );

        }

        finally {

            setLoading(false);

        }

    };

    return (

        <div className="min-h-screen bg-gray-100 flex justify-center items-center">

            <div className="bg-white w-full max-w-md rounded-2xl shadow-xl p-10">

                <h1 className="text-4xl font-bold text-center text-blue-700">

                    Login

                </h1>

                <p className="text-center text-gray-500 mt-3 mb-8">

                    Welcome back to IndusMart AI

                </p>

                <form
                    onSubmit={handleSubmit}
                    className="space-y-6"
                >

                    <div>

                        <label className="font-semibold">

                            Email

                        </label>

                        <input

                            type="email"

                            name="email"

                            value={formData.email}

                            onChange={handleChange}

                            required

                            className="w-full mt-2 border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                        />

                    </div>

                    <div>

                        <label className="font-semibold">

                            Password

                        </label>

                        <input

                            type="password"

                            name="password"

                            value={formData.password}

                            onChange={handleChange}

                            required

                            className="w-full mt-2 border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                        />

                    </div>

                    <button

                        type="submit"

                        disabled={loading}

                        className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg text-lg font-semibold transition"

                    >

                        {

                            loading

                                ? "Please Wait..."

                                : "Login"

                        }

                    </button>

                </form>

                <p className="text-center mt-8">

                    Don't have an account?

                    <Link

                        to="/register"

                        className="text-blue-600 font-semibold ml-2"

                    >

                        Register

                    </Link>

                </p>

            </div>

        </div>

    );

}

export default Login;