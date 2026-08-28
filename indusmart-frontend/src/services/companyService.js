import axiosConfig from "../api/axiosConfig";

/**
 * Register Company
 */
const registerCompany = async (company) => {

    const response = await axiosConfig.post(
        "/api/company/register",
        company
    );

    return response.data;
};

/**
 * Get Company By Id
 */
const getCompanyById = async (companyId) => {

    const response = await axiosConfig.get(
        `/api/company/${companyId}`
    );

    return response.data;
};

/**
 * Update Company
 */
const updateCompany = async (companyId, company) => {

    const response = await axiosConfig.put(
        `/api/company/update/${companyId}`,
        company
    );

    return response.data;
};

/**
 * Delete Company
 */
const deleteCompany = async (companyId) => {

    const response = await axiosConfig.delete(
        `/api/company/delete/${companyId}`
    );

    return response.data;
};

const companyService = {

    registerCompany,

    getCompanyById,

    updateCompany,

    deleteCompany

};

export default companyService;