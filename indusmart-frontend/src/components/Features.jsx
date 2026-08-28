function Features() {
  const features = [
    {
      title: "AI Product Recommendation",
      description:
        "Get intelligent equipment recommendations based on your business requirements using AI.",
      icon: "🤖",
    },
    {
      title: "Verified Manufacturers",
      description:
        "Purchase directly from trusted manufacturers and verified industrial suppliers.",
      icon: "🏭",
    },
    {
      title: "Secure Payments",
      description:
        "Experience safe online transactions with secure payment gateway integration.",
      icon: "🔐",
    },
    {
      title: "Equipment Rental",
      description:
        "Rent expensive industrial machinery whenever required instead of purchasing.",
      icon: "🚜",
    },
    {
      title: "Seller Dashboard",
      description:
        "Manage products, orders, inventory, subscriptions and customers from one dashboard.",
      icon: "📊",
    },
    {
      title: "Business Analytics",
      description:
        "Monitor sales, revenue, customer insights and business growth with analytics.",
      icon: "📈",
    },
  ];

  return (
    <section
      id="features"
      className="py-24 bg-white"
    >
      <div className="max-w-7xl mx-auto px-6">

        <div className="text-center mb-16">

          <h2 className="text-4xl font-bold text-gray-900">
            Why Choose IndusMart AI?
          </h2>

          <p className="text-gray-600 mt-4 text-lg max-w-3xl mx-auto">
            A complete AI-powered industrial marketplace built to simplify
            buying, selling, renting and managing industrial equipment.
          </p>

        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">

          {features.map((feature, index) => (

            <div
              key={index}
              className="bg-gray-50 rounded-2xl p-8 shadow-md hover:shadow-xl transition duration-300 hover:-translate-y-2"
            >

              <div className="text-5xl mb-6">
                {feature.icon}
              </div>

              <h3 className="text-2xl font-semibold mb-4">
                {feature.title}
              </h3>

              <p className="text-gray-600 leading-7">
                {feature.description}
              </p>

            </div>

          ))}

        </div>

      </div>
    </section>
  );
}

export default Features;