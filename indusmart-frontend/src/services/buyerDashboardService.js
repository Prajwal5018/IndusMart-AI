import axiosConfig from "../api/axiosConfig";

const getBuyerDashboard = async (buyerId) => {

    const response = await axiosConfig.get(

        `/api/buyer/dashboard/${buyerId}`

    );

    return response.data;

};

const buyerDashboardService = {

    getBuyerDashboard,

};

export default buyerDashboardService;