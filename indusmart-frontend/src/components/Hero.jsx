import { Link } from "react-router-dom";

function Hero() {

    return (

        <section className="bg-gray-100">

            <div className="max-w-7xl mx-auto px-6 py-20">

                <div className="grid md:grid-cols-2 gap-12 items-center">

                    {/* Left Side */}

                    <div>

                        <p className="text-blue-600 font-semibold mb-4">

                            AI Powered Industrial Marketplace

                        </p>

                        <h1 className="text-6xl font-bold leading-tight text-gray-900">

                            Buy, Sell & Rent

                            <br />

                            <span className="text-blue-600">

                                Industrial Equipment

                            </span>

                            <br />

                            with AI

                        </h1>

                        <p className="text-xl text-gray-600 mt-8 leading-10">

                            IndusMart AI connects industries,
                            manufacturers, suppliers and buyers
                            on one intelligent platform.

                            <br />

                            Discover equipment faster using AI
                            recommendations, compare products,
                            rent machinery and grow your business.

                        </p>

                        <div className="flex gap-5 mt-10">

                            <Link to="/products">

                                <button className="bg-blue-600 hover:bg-blue-700 text-white px-8 py-4 rounded-xl text-lg font-semibold transition">

                                    Explore Products

                                </button>

                            </Link>

                            <Link to="/register">

                                <button className="border-2 border-black hover:bg-black hover:text-white px-8 py-4 rounded-xl text-lg font-semibold transition">

                                    Become a Seller

                                </button>

                            </Link>

                        </div>

                    </div>

                    {/* Right Side */}

                    <div className="bg-white rounded-3xl shadow-2xl p-10">

                        <h2 className="text-4xl font-bold text-center mb-10">

                            Marketplace Highlights

                        </h2>

                        <div className="space-y-8 text-xl">

                            <div className="flex justify-between">

                                <span>Total Products</span>

                                <span className="font-bold text-blue-600">

                                    50,000+

                                </span>

                            </div>

                            <div className="flex justify-between">

                                <span>Verified Companies</span>

                                <span className="font-bold text-blue-600">

                                    2,000+

                                </span>

                            </div>

                            <div className="flex justify-between">

                                <span>AI Recommendations</span>

                                <span className="font-bold text-blue-600">

                                    Smart

                                </span>

                            </div>

                            <div className="flex justify-between">

                                <span>Secure Payments</span>

                                <span className="font-bold text-green-600">

                                    ✔ Enabled

                                </span>

                            </div>

                            <div className="flex justify-between">

                                <span>Support</span>

                                <span className="font-bold text-blue-600">

                                    24 × 7

                                </span>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </section>

    );

}

export default Hero;