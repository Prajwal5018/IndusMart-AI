import { useState } from "react";
import { Link } from "react-router-dom";

function Navbar() {

    const [menuOpen, setMenuOpen] = useState(false);

    return (

        <nav className="bg-white shadow-md sticky top-0 z-50">

            <div className="max-w-7xl mx-auto px-6">

                <div className="flex justify-between items-center h-20">

                    {/* Logo */}

                    <Link
                        to="/"
                        className="text-3xl font-bold text-blue-700"
                    >
                        IndusMart AI
                    </Link>

                    {/* Desktop Menu */}

                    <div className="hidden md:flex items-center gap-8">

                        <a
                            href="/#"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            Home
                        </a>

                        <a
                            href="#features"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            Features
                        </a>

                        <a
                            href="#categories"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            Categories
                        </a>

                        <a
                            href="#about"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            About
                        </a>

                        <a
                            href="#contact"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            Contact
                        </a>

                        <Link
                            to="/products"
                            className="text-gray-700 hover:text-blue-600 transition"
                        >
                            Products
                        </Link>

                    </div>

                    {/* Buttons */}

                    <div className="hidden md:flex gap-4">

                        <Link to="/login">

                            <button className="px-5 py-2 border border-blue-600 rounded-lg text-blue-600 hover:bg-blue-600 hover:text-white transition">

                                Login

                            </button>

                        </Link>

                        <Link to="/register">

                            <button className="px-5 py-2 rounded-lg bg-blue-600 text-white hover:bg-blue-700 transition">

                                Register

                            </button>

                        </Link>

                    </div>

                    {/* Mobile Menu Button */}

                    <button
                        className="md:hidden text-3xl"
                        onClick={() => setMenuOpen(!menuOpen)}
                    >
                        ☰
                    </button>

                </div>

                {/* Mobile Menu */}

                {menuOpen && (

                    <div className="md:hidden pb-6">

                        <div className="flex flex-col gap-5">

                            <a href="/#">Home</a>

                            <a href="#features">Features</a>

                            <a href="#categories">Categories</a>

                            <a href="#about">About</a>

                            <a href="#contact">Contact</a>

                            <Link to="/products">
                                Products
                            </Link>

                            <Link to="/login">

                                <button className="border border-blue-600 text-blue-600 py-2 rounded-lg w-full">

                                    Login

                                </button>

                            </Link>

                            <Link to="/register">

                                <button className="bg-blue-600 text-white py-2 rounded-lg w-full">

                                    Register

                                </button>

                            </Link>

                        </div>

                    </div>

                )}

            </div>

        </nav>

    );

}

export default Navbar;