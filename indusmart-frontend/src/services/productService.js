import axiosConfig from "../api/axiosConfig";

/**
 * Get All Products
 */
const getAllProducts = async () => {

    const response = await axiosConfig.get(
        "/api/product/all"
    );

    return response.data;
};

/**
 * Get Product By Id
 */
const getProductById = async (productId) => {

    const response = await axiosConfig.get(
        `/api/product/${productId}`
    );

    return response.data;
};

/**
 * Add Product
 */
const addProduct = async (product) => {

    const response = await axiosConfig.post(
        "/api/product/add",
        product
    );

    return response.data;
};

/**
 * Update Product
 */
const updateProduct = async (productId, product) => {

    const response = await axiosConfig.put(
        `/api/product/update/${productId}`,
        product
    );

    return response.data;
};

/**
 * Delete Product
 */
const deleteProduct = async (productId) => {

    const response = await axiosConfig.delete(
        `/api/product/delete/${productId}`
    );

    return response.data;
};

/**
 * Search By Product Name
 */
const searchByName = async (keyword) => {

    const response = await axiosConfig.get(
        "/api/product/search/name",
        {
            params: {
                keyword
            }
        }
    );

    return response.data;
};

/**
 * Search By Brand
 */
const searchByBrand = async (brand) => {

    const response = await axiosConfig.get(
        "/api/product/search/brand",
        {
            params: {
                brand
            }
        }
    );

    return response.data;
};

/**
 * Search By Category
 */
const searchByCategory = async (category) => {

    const response = await axiosConfig.get(
        "/api/product/search/category",
        {
            params: {
                category
            }
        }
    );

    return response.data;
};

/**
 * Get Products By Company
 */
const getProductsByCompany = async (companyId) => {

    const response = await axiosConfig.get(
        `/api/product/company/${companyId}`
    );

    return response.data;
};

const productService = {

    getAllProducts,

    getProductById,

    addProduct,

    updateProduct,

    deleteProduct,

    searchByName,

    searchByBrand,

    searchByCategory,

    getProductsByCompany

};

export default productService;