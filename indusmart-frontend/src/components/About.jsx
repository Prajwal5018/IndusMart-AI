function About() {

    return (

        <section
            id="about"
            className="py-20 bg-white"
        >

            <div className="max-w-7xl mx-auto px-6">

                <div className="text-center mb-16">

                    <h2 className="text-5xl font-bold">

                        About IndusMart AI

                    </h2>

                    <p className="mt-6 text-xl text-gray-600">

                        India's AI Powered Industrial Equipment Marketplace

                    </p>

                </div>

                <div className="grid md:grid-cols-2 gap-16 items-center">

                    <div>

                        <img
                            src="https://images.unsplash.com/photo-1565008447742-97f6f38c985c?w=900"
                            alt="Industrial Equipment"
                            className="rounded-3xl shadow-2xl"
                        />

                    </div>

                    <div>

                        <h3 className="text-4xl font-bold mb-8">

                            One Platform for Buying, Selling & Renting Industrial Equipment

                        </h3>

                        <p className="text-lg text-gray-600 leading-9">

                            IndusMart AI is an intelligent industrial marketplace
                            developed to simplify industrial procurement,
                            equipment rental and product discovery using Artificial Intelligence.

                        </p>

                        <p className="text-lg text-gray-600 leading-9 mt-6">

                            Manufacturers, suppliers and buyers can connect
                            through one secure platform with smart product
                            recommendations, verified sellers, secure online
                            payments and real-time order tracking.

                        </p>

                        <div className="grid grid-cols-2 gap-6 mt-10">

                            <div className="bg-blue-50 p-6 rounded-xl">

                                <h2 className="text-4xl font-bold text-blue-600">

                                    50K+

                                </h2>

                                <p className="mt-2">

                                    Products

                                </p>

                            </div>

                            <div className="bg-green-50 p-6 rounded-xl">

                                <h2 className="text-4xl font-bold text-green-600">

                                    2000+

                                </h2>

                                <p className="mt-2">

                                    Verified Companies

                                </p>

                            </div>

                            <div className="bg-purple-50 p-6 rounded-xl">

                                <h2 className="text-4xl font-bold text-purple-600">

                                    AI

                                </h2>

                                <p className="mt-2">

                                    Smart Recommendations

                                </p>

                            </div>

                            <div className="bg-yellow-50 p-6 rounded-xl">

                                <h2 className="text-4xl font-bold text-yellow-600">

                                    24×7

                                </h2>

                                <p className="mt-2">

                                    Customer Support

                                </p>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </section>

    );

}

export default About;