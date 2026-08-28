import axiosConfig from "../api/axiosConfig";

/**
 * Add Product To Cart
 */
const addToCart = async (
    buyerId,
    productId,
    quantity,
    rental,
    rentalDays
) => {

    const response = await axiosConfig.post(

        `/api/cart/add`,

        null,

        {

            params: {

                buyerId,

                productId,

                quantity,

                rental,

                rentalDays

            }

        }

    );

    return response.data;

};

/**
 * Get Buyer's Cart
 */
const getCart = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/cart/${buyerId}`

    );

    return response.data;

};

/**
 * Update Quantity
 */
const updateQuantity = async (

    cartId,

    quantity

) => {

    const response = await axiosConfig.put(

        `/api/cart/update`,

        null,

        {

            params: {

                cartId,

                quantity

            }

        }

    );

    return response.data;

};

/**
 * Remove Item
 */
const removeItem = async (cartId) => {

    const response = await axiosConfig.delete(

        `/api/cart/remove/${cartId}`

    );

    return response.data;

};

/**
 * Clear Cart
 */
const clearCart = async (buyerId) => {

    const response = await axiosConfig.delete(

        `/api/cart/clear/${buyerId}`

    );

    return response.data;

};

/**
 * Cart Total
 */
const getCartTotal = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/cart/total/${buyerId}`

    );

    return response.data;

};

const cartService = {

    addToCart,

    getCart,

    updateQuantity,

    removeItem,

    clearCart,

    getCartTotal

};

export default cartService;