function Stats() {

  const stats = [

    {
      value: "50,000+",
      title: "Industrial Products",
    },

    {
      value: "2,000+",
      title: "Verified Companies",
    },

    {
      value: "25,000+",
      title: "Happy Customers",
    },

    {
      value: "100,000+",
      title: "Orders Completed",
    },

    {
      value: "500+",
      title: "Cities Served",
    },

    {
      value: "24 × 7",
      title: "Customer Support",
    },

  ];

  return (

    <section className="py-24 bg-blue-700 text-white">

      <div className="max-w-7xl mx-auto px-6">

        <div className="text-center mb-16">

          <h2 className="text-4xl font-bold">
            IndusMart AI in Numbers
          </h2>

          <p className="text-blue-100 mt-4 text-lg max-w-3xl mx-auto">
            Empowering industries with AI-powered buying, selling and
            rental solutions across India.
          </p>

        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">

          {stats.map((item, index) => (

            <div
              key={index}
              className="bg-white text-gray-900 rounded-2xl p-10 shadow-xl text-center hover:scale-105 transition duration-300"
            >

              <h3 className="text-5xl font-bold text-blue-600 mb-4">

                {item.value}

              </h3>

              <p className="text-xl font-semibold">

                {item.title}

              </p>

            </div>

          ))}

        </div>

      </div>

    </section>

  );

}

export default Stats;