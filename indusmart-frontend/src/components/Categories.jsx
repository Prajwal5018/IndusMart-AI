import { Link } from "react-router-dom";

const categories = [

    {
        icon: "⚙️",
        title: "Industrial Machinery",
        description:
            "CNC machines, generators, compressors, lathes and manufacturing equipment."
    },

    {
        icon: "⚡",
        title: "Electrical Equipment",
        description:
            "Motors, transformers, cables, switchgear and industrial electrical products."
    },

    {
        icon: "🏗️",
        title: "Construction Equipment",
        description:
            "Excavators, cranes, concrete mixers, loaders and heavy construction machines."
    },

    {
        icon: "🚚",
        title: "Material Handling",
        description:
            "Forklifts, pallet trucks, conveyors, stackers and warehouse equipment."
    },

    {
        icon: "🔩",
        title: "Tools & Hardware",
        description:
            "Power tools, welding machines, cutting equipment and industrial hardware."
    },

    {
        icon: "🧪",
        title: "Industrial Chemicals",
        description:
            "Lubricants, coatings, adhesives, cleaning solutions and specialty chemicals."
    }

];

function Categories() {

    return (

        <section
            id="categories"
            className="py-20 bg-gray-100"
        >

            <div className="max-w-7xl mx-auto px-6">

                <div className="text-center mb-14">

                    <h2 className="text-5xl font-bold">

                        Explore Categories

                    </h2>

                    <p className="text-gray-600 mt-5 text-lg">

                        Browse thousands of industrial products across multiple
                        categories designed for manufacturing, construction,
                        automation and heavy industries.

                    </p>

                </div>

                <div className="grid md:grid-cols-3 gap-8">

                    {

                        categories.map((category, index) => (

                            <div
                                key={index}
                                className="bg-white rounded-2xl shadow-lg p-8 hover:shadow-2xl transition duration-300"
                            >

                                <div className="text-6xl mb-5">

                                    {category.icon}

                                </div>

                                <h3 className="text-2xl font-bold mb-4">

                                    {category.title}

                                </h3>

                                <p className="text-gray-600 leading-7 mb-8">

                                    {category.description}

                                </p>

                                <Link to="/products">

                                    <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg transition">

                                        Browse Products

                                    </button>

                                </Link>

                            </div>

                        ))

                    }

                </div>

            </div>

        </section>

    );

}

export default Categories;