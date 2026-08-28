function Footer() {
  return (
    <footer
      id="contact"
      className="bg-gray-900 text-white pt-16 pb-8"
    >
      <div className="max-w-7xl mx-auto px-6">

        <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-10">

          {/* Company */}

          <div>

            <h2 className="text-3xl font-bold text-blue-500 mb-5">
              IndusMart AI
            </h2>

            <p className="text-gray-400 leading-7">

              AI Powered Industrial Equipment Marketplace for
              buying, selling and renting industrial machinery
              with secure payments and intelligent
              recommendations.

            </p>

          </div>

          {/* Quick Links */}

          <div>

            <h3 className="text-xl font-semibold mb-5">
              Quick Links
            </h3>

            <ul className="space-y-3 text-gray-400">

              <li>
                <a href="#" className="hover:text-white">
                  Home
                </a>
              </li>

              <li>
                <a href="#features" className="hover:text-white">
                  Features
                </a>
              </li>

              <li>
                <a href="#categories" className="hover:text-white">
                  Categories
                </a>
              </li>

              <li>
                <a href="#" className="hover:text-white">
                  Products
                </a>
              </li>

              <li>
                <a href="#" className="hover:text-white">
                  Contact
                </a>
              </li>

            </ul>

          </div>

          {/* Categories */}

          <div>

            <h3 className="text-xl font-semibold mb-5">
              Categories
            </h3>

            <ul className="space-y-3 text-gray-400">

              <li>Industrial Machinery</li>

              <li>Construction Equipment</li>

              <li>Electrical Equipment</li>

              <li>Material Handling</li>

              <li>Industrial Chemicals</li>

            </ul>

          </div>

          {/* Contact */}

          <div>

            <h3 className="text-xl font-semibold mb-5">
              Contact
            </h3>

            <div className="space-y-4 text-gray-400">

              <p>
                📧 support@indusmartai.com
              </p>

              <p>
                📞 +91 98765 43210
              </p>

              <p>
                📍 Mangalore, Karnataka, India
              </p>

              <div className="flex gap-4 mt-5 text-2xl">

                <span className="cursor-pointer hover:text-blue-400">
                  🌐
                </span>

                <span className="cursor-pointer hover:text-blue-400">
                  💼
                </span>

                <span className="cursor-pointer hover:text-blue-400">
                  📘
                </span>

                <span className="cursor-pointer hover:text-blue-400">
                  ▶️
                </span>

              </div>

            </div>

          </div>

        </div>

        <hr className="border-gray-700 my-10" />

        <div className="text-center text-gray-500">

          © 2026 <span className="text-blue-400 font-semibold">
            IndusMart AI
          </span>. All Rights Reserved.

          <br />

          Developed using React, Spring Boot, MySQL & AI.

        </div>

      </div>
    </footer>
  );
}

export default Footer;