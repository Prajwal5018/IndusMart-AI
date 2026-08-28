import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import productService from "../../services/productService";

function ProductList() {

    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    const [keyword, setKeyword] = useState("");
    const [category, setCategory] = useState("");

    useEffect(() => {

        loadProducts();

    }, []);

    const loadProducts = async () => {

        try {

            const data = await productService.getAllProducts();

            setProducts(data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const searchByName = async () => {

        if (keyword.trim() === "") {

            loadProducts();

            return;

        }

        try {

            const data = await productService.searchByName(keyword);

            setProducts(data);

        }

        catch (error) {

            console.log(error);

        }

    };

    const searchByCategory = async () => {

        if (category === "") {

            loadProducts();

            return;

        }

        try {

            const data = await productService.searchByCategory(category);

            setProducts(data);

        }

        catch (error) {

            console.log(error);

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Products...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto px-8 py-10">

                <div className="flex flex-col md:flex-row justify-between gap-4 mb-8">

                    <h1 className="text-4xl font-bold">

                        Products

                    </h1>

                    <div className="flex gap-3">

                        <input

                            type="text"

                            placeholder="Search Product"

                            value={keyword}

                            onChange={(e) =>

                                setKeyword(e.target.value)

                            }

                            className="border rounded-lg px-4 py-2"

                        />

                        <button

                            onClick={searchByName}

                            className="bg-blue-600 text-white px-5 rounded-lg"

                        >

                            Search

                        </button>

                    </div>

                </div>

                <div className="mb-8">

                    <select

                        value={category}

                        onChange={(e) => {

                            setCategory(e.target.value);

                        }}

                        className="border rounded-lg px-4 py-3"

                    >

                        <option value="">

                            All Categories

                        </option>

                        <option value="Machinery">

                            Machinery

                        </option>

                        <option value="Electrical">

                            Electrical

                        </option>

                        <option value="Construction">

                            Construction

                        </option>

                        <option value="Tools">

                            Tools

                        </option>

                    </select>

                    <button

                        onClick={searchByCategory}

                        className="ml-4 bg-green-600 text-white px-5 py-3 rounded-lg"

                    >

                        Filter

                    </button>

                </div>

                {

                    products.length === 0 ?

                        (

                            <div className="bg-white rounded-xl shadow p-10 text-center">

                                <h2 className="text-2xl">

                                    No Products Found

                                </h2>

                            </div>

                        )

                        :

                        (

                            <div className="grid lg:grid-cols-4 md:grid-cols-2 gap-8">

                                {

                                    products.map(product => (

                                        <div

                                            key={product.id}

                                            className="bg-white rounded-xl shadow hover:shadow-xl duration-300 overflow-hidden"

                                        >

                                            <img

                                                src={

                                                    product.imageUrl ||

                                                    "https://placehold.co/600x400?text=No+Image"

                                                }

                                                alt={product.productName}

                                                className="w-full h-52 object-cover"

                                            />

                                            <div className="p-5">

                                                <h2 className="text-xl font-bold">

                                                    {product.productName}

                                                </h2>

                                                <p className="text-gray-500 mt-2">

                                                    {product.brand}

                                                </p>

                                                <p className="text-sm text-gray-500">

                                                    {product.category}

                                                </p>

                                                {

                                                    product.availableForSale &&

                                                    <h2 className="text-blue-700 font-bold mt-4">

                                                        ₹ {product.purchasePrice}

                                                    </h2>

                                                }

                                                {

                                                    product.availableForRent &&

                                                    <h3 className="text-green-700 font-semibold">

                                                        Rent ₹ {product.rentalPricePerDay}/day

                                                    </h3>

                                                }

                                                <Link

                                                    to={`/products/${product.id}`}

                                                    className="block mt-5"

                                                >

                                                    <button

                                                        className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg"

                                                    >

                                                        View Details

                                                    </button>

                                                </Link>

                                            </div>

                                        </div>

                                    ))

                                }

                            </div>

                        )

                }

            </div>

        </div>

    );

}

export default ProductList;