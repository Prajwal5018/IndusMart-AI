import { useState } from "react";
import { useNavigate } from "react-router-dom";
import sellerService from "../../services/sellerService";

function AddProduct() {

    const navigate = useNavigate();

    /*
     * Temporary
     * Later get companyId from JWT
     */
    const companyId = 1;

    const [loading, setLoading] = useState(false);

    const [product, setProduct] = useState({

        productName: "",

        description: "",

        purchasePrice: "",

        rentalPricePerDay: "",

        quantity: "",

        category: "",

        brand: "",

        modelNumber: "",

        imageUrl: "",

        availableForRent: false,

        availableForSale: true,

        companyId: companyId

    });

    const handleChange = (e) => {

        const { name, value, type, checked } = e.target;

        setProduct({

            ...product,

            [name]: type === "checkbox"
                ? checked
                : value

        });

    };

    const validateForm = () => {

        if (product.productName.trim().length < 3) {

            alert("Product name must be at least 3 characters.");

            return false;

        }

        if (product.description.trim().length < 20) {

            alert("Description must contain at least 20 characters.");

            return false;

        }

        if (Number(product.purchasePrice) <= 0) {

            alert("Purchase price must be greater than zero.");

            return false;

        }

        if (Number(product.rentalPricePerDay) <= 0) {

            alert("Rental price must be greater than zero.");

            return false;

        }

        if (Number(product.quantity) <= 0) {

            alert("Quantity must be greater than zero.");

            return false;

        }

        if (product.category.trim() === "") {

            alert("Category is required.");

            return false;

        }

        if (product.brand.trim() === "") {

            alert("Brand is required.");

            return false;

        }

        if (product.modelNumber.trim() === "") {

            alert("Model Number is required.");

            return false;

        }

        return true;

    };

    const saveProduct = async (e) => {

        e.preventDefault();

        if (!validateForm()) {

            return;

        }

        setLoading(true);

        try {

            await sellerService.addProduct({

                ...product,

                purchasePrice: Number(product.purchasePrice),

                rentalPricePerDay: Number(product.rentalPricePerDay),

                quantity: Number(product.quantity),

                companyId

            });

            alert("Product Added Successfully");

            navigate("/seller/dashboard");

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to Add Product"

            );

        }

        finally {

            setLoading(false);

        }

    };

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-5xl mx-auto py-10">

                <div className="bg-white rounded-2xl shadow-xl p-10">

                    <h1 className="text-4xl font-bold mb-10">

                        Add New Product

                    </h1>

                    <form

                        onSubmit={saveProduct}

                        className="grid md:grid-cols-2 gap-8"

                    >

                        <div>

                            <label className="font-semibold">

                                Product Name

                            </label>

                            <input

                                type="text"

                                name="productName"

                                value={product.productName}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                        <div>

                            <label className="font-semibold">

                                Brand

                            </label>

                            <input

                                type="text"

                                name="brand"

                                value={product.brand}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                        <div>

                            <label className="font-semibold">

                                Model Number

                            </label>

                            <input

                                type="text"

                                name="modelNumber"

                                value={product.modelNumber}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                        <div>

                            <label className="font-semibold">

                                Category

                            </label>

                            <input

                                type="text"

                                name="category"

                                value={product.category}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                        <div>

                            <label className="font-semibold">

                                Purchase Price

                            </label>

                            <input

                                type="number"

                                name="purchasePrice"

                                value={product.purchasePrice}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                        <div>

                            <label className="font-semibold">

                                Rental Price / Day

                            </label>

                            <input

                                type="number"

                                name="rentalPricePerDay"

                                value={product.rentalPricePerDay}

                                onChange={handleChange}

                                className="w-full mt-2 border rounded-lg px-4 py-3"

                                required

                            />

                        </div>

                                                <div>

                                                    <label className="font-semibold">

                                                        Quantity

                                                    </label>

                                                    <input

                                                        type="number"

                                                        name="quantity"

                                                        value={product.quantity}

                                                        onChange={handleChange}

                                                        className="w-full mt-2 border rounded-lg px-4 py-3"

                                                        required

                                                    />

                                                </div>

                                                <div className="md:col-span-2">

                                                    <label className="font-semibold">

                                                        Description

                                                    </label>

                                                    <textarea

                                                        rows="6"

                                                        name="description"

                                                        value={product.description}

                                                        onChange={handleChange}

                                                        className="w-full mt-2 border rounded-lg px-4 py-3"

                                                        required

                                                    />

                                                </div>

                                                <div className="md:col-span-2">

                                                    <label className="font-semibold">

                                                        Product Image URL

                                                    </label>

                                                    <input

                                                        type="text"

                                                        name="imageUrl"

                                                        value={product.imageUrl}

                                                        onChange={handleChange}

                                                        placeholder="https://example.com/image.jpg"

                                                        className="w-full mt-2 border rounded-lg px-4 py-3"

                                                    />

                                                </div>

                                                <div className="flex items-center gap-8">

                                                    <label className="flex items-center gap-3">

                                                        <input

                                                            type="checkbox"

                                                            name="availableForSale"

                                                            checked={product.availableForSale}

                                                            onChange={handleChange}

                                                        />

                                                        Available For Sale

                                                    </label>

                                                    <label className="flex items-center gap-3">

                                                        <input

                                                            type="checkbox"

                                                            name="availableForRent"

                                                            checked={product.availableForRent}

                                                            onChange={handleChange}

                                                        />

                                                        Available For Rent

                                                    </label>

                                                </div>

                                                <div></div>

                                                <button

                                                    type="submit"

                                                    disabled={loading}

                                                    className="bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-xl text-lg font-semibold"

                                                >

                                                    {

                                                        loading

                                                            ? "Adding Product..."

                                                            : "Add Product"

                                                    }

                                                </button>

                                                <button

                                                    type="button"

                                                    onClick={() => navigate("/seller/dashboard")}

                                                    className="bg-gray-600 hover:bg-gray-700 text-white py-4 rounded-xl text-lg font-semibold"

                                                >

                                                    Cancel

                                                </button>

                                            </form>

                                        </div>

                                    </div>

                                </div>

                            );

                        }

                        export default AddProduct;