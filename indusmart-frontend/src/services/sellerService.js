import axiosConfig from "../api/axiosConfig";

/**
 * Seller Dashboard
 */
const getDashboard = async (companyId) => {

    const response = await axiosConfig.get(

        `/api/seller/dashboard/${companyId}`

    );

    return response.data;

};

/**
 * Company Products
 */
const getProducts = async (companyId) => {

    const response = await axiosConfig.get(

        `/api/product/company/${companyId}`

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
const updateProduct = async (

    productId,

    product

) => {

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

const sellerService = {

    getDashboard,

    getProducts,

    addProduct,

    updateProduct,

    deleteProduct

};

export default sellerService;