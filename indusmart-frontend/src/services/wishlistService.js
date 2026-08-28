import axiosConfig from "../api/axiosConfig";

/**
 * Add Product To Wishlist
 */
const addToWishlist = async (buyerId, productId) => {

    const response = await axiosConfig.post(

        "/api/wishlist/add",

        null,

        {

            params: {

                buyerId,

                productId

            }

        }

    );

    return response.data;

};

/**
 * Remove Product From Wishlist
 */
const removeFromWishlist = async (

    buyerId,

    productId

) => {

    const response = await axiosConfig.delete(

        "/api/wishlist/remove",

        {

            params: {

                buyerId,

                productId

            }

        }

    );

    return response.data;

};

/**
 * Get Buyer's Wishlist
 */
const getWishlist = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/wishlist/${buyerId}`

    );

    return response.data;

};

/**
 * Check Product Exists In Wishlist
 */
const isWishlisted = async (

    buyerId,

    productId

) => {

    const response = await axiosConfig.get(

        "/api/wishlist/check",

        {

            params: {

                buyerId,

                productId

            }

        }

    );

    return response.data;

};

/**
 * Wishlist Count
 */
const wishlistCount = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/wishlist/count/${buyerId}`

    );

    return response.data;

};

/**
 * Clear Wishlist
 */
const clearWishlist = async (buyerId) => {

    const response = await axiosConfig.delete(

        `/api/wishlist/clear/${buyerId}`

    );

    return response.data;

};

/**
 * Product Wishlist Count
 */
const productWishlistCount = async (

    productId

) => {

    const response = await axiosConfig.get(

        `/api/wishlist/product-count/${productId}`

    );

    return response.data;

};

const wishlistService = {

    addToWishlist,

    removeFromWishlist,

    getWishlist,

    isWishlisted,

    wishlistCount,

    clearWishlist,

    productWishlistCount

};

export default wishlistService;