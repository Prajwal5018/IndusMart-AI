import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import productService from "../../services/productService";

function EditProduct() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);

    const [saving, setSaving] = useState(false);

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

        availableForRent: true,

        availableForSale: true,

        companyId: 1

    });

    useEffect(() => {

        loadProduct();

    }, []);

    const loadProduct = async () => {

        try {

            const data =
                await productService.getProductById(id);

            setProduct({

                productName: data.productName,

                description: data.description,

                purchasePrice: data.purchasePrice,

                rentalPricePerDay: data.rentalPricePerDay,

                quantity: data.quantity,

                category: data.category,

                brand: data.brand,

                modelNumber: data.modelNumber,

                imageUrl: data.imageUrl,

                availableForRent: data.availableForRent,

                availableForSale: data.availableForSale,

                companyId: data.company.id

            });

        }

        catch (error) {

            alert("Unable to load product.");

            navigate("/seller/dashboard");

        }

        finally {

            setLoading(false);

        }

    };

    const handleChange = (e) => {

        const { name, value, type, checked } = e.target;

        setProduct({

            ...product,

            [name]:

                type === "checkbox"

                    ? checked

                    : value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        setSaving(true);

        try {

            await productService.updateProduct(

                id,

                product

            );

            alert("Product Updated Successfully");

            navigate("/seller/dashboard");

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Failed to update product."

            );

        }

        finally {

            setSaving(false);

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Product...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100 py-10">

            <div className="max-w-4xl mx-auto bg-white rounded-xl shadow-lg p-8">

                <h1 className="text-4xl font-bold text-blue-700 mb-8">

                    Edit Product

                </h1>

                <form
                    onSubmit={handleSubmit}
                    className="grid grid-cols-2 gap-6"
                >

                    <input

                        type="text"

                        name="productName"

                        placeholder="Product Name"

                        value={product.productName}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="text"

                        name="brand"

                        placeholder="Brand"

                        value={product.brand}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <textarea

                        name="description"

                        placeholder="Description"

                        value={product.description}

                        onChange={handleChange}

                        className="border rounded-lg p-3 col-span-2"

                        rows="5"

                        required

                    />

                    <input

                        type="number"

                        name="purchasePrice"

                        placeholder="Purchase Price"

                        value={product.purchasePrice}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="number"

                        name="rentalPricePerDay"

                        placeholder="Rental Price"

                        value={product.rentalPricePerDay}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="number"

                        name="quantity"

                        placeholder="Quantity"

                        value={product.quantity}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="text"

                        name="category"

                        placeholder="Category"

                        value={product.category}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="text"

                        name="modelNumber"

                        placeholder="Model Number"

                        value={product.modelNumber}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                        required

                    />

                    <input

                        type="text"

                        name="imageUrl"

                        placeholder="Image URL"

                        value={product.imageUrl}

                        onChange={handleChange}

                        className="border rounded-lg p-3"

                    />

                    <div className="col-span-2 flex gap-10">

                        <label className="flex items-center gap-2">

                            <input

                                type="checkbox"

                                name="availableForSale"

                                checked={product.availableForSale}

                                onChange={handleChange}

                            />

                            Available For Sale

                        </label>

                        <label className="flex items-center gap-2">

                            <input

                                type="checkbox"

                                name="availableForRent"

                                checked={product.availableForRent}

                                onChange={handleChange}

                            />

                            Available For Rent

                        </label>

                    </div>

                    <button

                        type="submit"

                        disabled={saving}

                        className="col-span-2 bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-lg text-lg"

                    >

                        {

                            saving

                                ? "Updating..."

                                : "Update Product"

                        }

                    </button>

                </form>

            </div>

        </div>

    );

}

export default EditProduct;