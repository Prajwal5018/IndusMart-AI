import axiosConfig from "../api/axiosConfig";

/**
 * Checkout
 */
const checkout = async (checkoutRequest) => {

    const response = await axiosConfig.post(

        "/api/orders/checkout",

        checkoutRequest

    );

    return response.data;

};

/**
 * Buyer Order History
 */
const getBuyerOrders = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/orders/buyer/${buyerId}`

    );

    return response.data;

};

/**
 * Get Order By ID
 */
const getOrder = async (orderId) => {

    const response = await axiosConfig.get(

        `/api/orders/${orderId}`

    );

    return response.data;

};

/**
 * Cancel Order
 */
const cancelOrder = async (orderId) => {

    const response = await axiosConfig.put(

        `/api/orders/cancel/${orderId}`

    );

    return response.data;

};

/**
 * Update Order Status
 */
const updateOrderStatus = async (

    orderId,

    status

) => {

    const response = await axiosConfig.put(

        `/api/orders/status/${orderId}`,

        null,

        {

            params: {

                status

            }

        }

    );

    return response.data;

};

/**
 * Update Payment Status
 */
const updatePaymentStatus = async (

    orderId,

    status

) => {

    const response = await axiosConfig.put(

        `/api/orders/payment/${orderId}`,

        null,

        {

            params: {

                status

            }

        }

    );

    return response.data;

};

const orderService = {

    checkout,

    getBuyerOrders,

    getOrder,

    cancelOrder,

    updateOrderStatus,

    updatePaymentStatus

};

export default orderService;