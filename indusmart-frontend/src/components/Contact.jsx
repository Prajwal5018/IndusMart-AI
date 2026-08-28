function Contact() {

    return (

        <section
            id="contact"
            className="py-20 bg-gray-100"
        >

            <div className="max-w-7xl mx-auto px-6">

                <div className="text-center mb-16">

                    <h2 className="text-5xl font-bold">

                        Contact Us

                    </h2>

                    <p className="mt-6 text-xl text-gray-600">

                        We'd love to hear from you

                    </p>

                </div>

                <div className="grid md:grid-cols-2 gap-12">

                    {/* Contact Details */}

                    <div className="bg-white rounded-2xl shadow-xl p-10">

                        <h3 className="text-3xl font-bold mb-8">

                            Get In Touch

                        </h3>

                        <div className="space-y-8">

                            <div>

                                <h4 className="font-bold text-blue-600">

                                    📍 Address

                                </h4>

                                <p className="mt-2 text-gray-600">

                                    Mangalore, Karnataka, India

                                </p>

                            </div>

                            <div>

                                <h4 className="font-bold text-blue-600">

                                    📧 Email

                                </h4>

                                <p className="mt-2 text-gray-600">

                                    support@indusmartai.com

                                </p>

                            </div>

                            <div>

                                <h4 className="font-bold text-blue-600">

                                    📞 Phone

                                </h4>

                                <p className="mt-2 text-gray-600">

                                    +91 9876543210

                                </p>

                            </div>

                            <div>

                                <h4 className="font-bold text-blue-600">

                                    🕒 Working Hours

                                </h4>

                                <p className="mt-2 text-gray-600">

                                    Monday - Saturday

                                    <br />

                                    9:00 AM - 6:00 PM

                                </p>

                            </div>

                        </div>

                    </div>

                    {/* Contact Form */}

                    <div className="bg-white rounded-2xl shadow-xl p-10">

                        <h3 className="text-3xl font-bold mb-8">

                            Send Message

                        </h3>

                        <form className="space-y-6">

                            <input

                                type="text"

                                placeholder="Your Name"

                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                            />

                            <input

                                type="email"

                                placeholder="Your Email"

                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                            />

                            <input

                                type="text"

                                placeholder="Subject"

                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                            />

                            <textarea

                                rows="5"

                                placeholder="Your Message"

                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500"

                            ></textarea>

                            <button

                                type="submit"

                                className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg text-lg font-semibold transition"

                            >

                                Send Message

                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </section>

    );

}

export default Contact;