import axiosConfig from "../api/axiosConfig";

/**
 * Create Razorpay Order
 */
const createOrder = async (paymentRequest) => {

    const response = await axiosConfig.post(

        "/api/payment/create-order",

        paymentRequest

    );

    return response.data;

};

/**
 * Verify Razorpay Payment
 */
const verifyPayment = async (

    orderId,

    razorpay_order_id,

    razorpay_payment_id,

    razorpay_signature

) => {

    const response = await axiosConfig.post(

        `/api/payment/verify/${orderId}`,

        {

            razorpay_order_id,

            razorpay_payment_id,

            razorpay_signature

        }

    );

    return response.data;

};

/**
 * Get Payment Status
 */
const getPaymentStatus = async (orderId) => {

    const response = await axiosConfig.get(

        `/api/payment/status/${orderId}`

    );

    return response.data;

};

const paymentService = {

    createOrder,

    verifyPayment,

    getPaymentStatus

};

export default paymentService;