import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        fullName: "",
        email: "",
        phone: "",
        password: "",
        role: "BUYER"
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

            await axios.post(
                "http://localhost:8081/api/auth/register",
                formData
            );

            alert("Registration Successful");

            navigate("/login");

        } catch (error) {

            console.log(error.response);

            alert(
                error.response?.data?.message ||
                "Registration Failed"
            );

        } finally {

            setLoading(false);

        }
    };

    return (

        <div className="min-h-screen flex justify-center items-center bg-gray-100">

            <div className="bg-white p-10 rounded-2xl shadow-xl w-full max-w-lg">

                <h1 className="text-4xl font-bold text-center text-blue-700">
                    Create Account
                </h1>

                <p className="text-center text-gray-500 mt-3 mb-8">
                    Join IndusMart AI
                </p>

                <form
                    onSubmit={handleSubmit}
                    className="space-y-5"
                >

                    <div>

                        <label className="font-semibold">
                            Full Name
                        </label>

                        <input
                            type="text"
                            name="fullName"
                            value={formData.fullName}
                            onChange={handleChange}
                            required
                            className="w-full mt-2 border rounded-lg px-4 py-3"
                        />

                    </div>

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
                            className="w-full mt-2 border rounded-lg px-4 py-3"
                        />

                    </div>

                    <div>

                        <label className="font-semibold">
                            Phone Number
                        </label>

                        <input
                            type="text"
                            name="phone"
                            value={formData.phone}
                            onChange={handleChange}
                            required
                            className="w-full mt-2 border rounded-lg px-4 py-3"
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
                            minLength={8}
                            className="w-full mt-2 border rounded-lg px-4 py-3"
                        />

                    </div>

                    <div>

                        <label className="font-semibold">
                            Role
                        </label>

                        <select
                            name="role"
                            value={formData.role}
                            onChange={handleChange}
                            className="w-full mt-2 border rounded-lg px-4 py-3"
                        >
                            <option value="BUYER">Buyer</option>
                            <option value="SELLER">Seller</option>
                        </select>

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                        className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg text-lg"
                    >
                        {loading ? "Please Wait..." : "Register"}
                    </button>

                </form>

                <p className="text-center mt-8">

                    Already have an account?

                    <Link
                        to="/login"
                        className="text-blue-600 font-semibold ml-2"
                    >
                        Login
                    </Link>

                </p>

            </div>

        </div>

    );
}

export default Register;