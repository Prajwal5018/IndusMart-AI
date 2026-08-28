import { useEffect, useState } from "react";
import companyService from "../../services/companyService";

function CompanyProfile() {

    /*
     * Temporary
     * Later from JWT
     */
    const companyId = 1;

    const ownerId = 1;

    const [loading, setLoading] = useState(true);

    const [saving, setSaving] = useState(false);

    const [company, setCompany] = useState({

        companyName: "",

        gstNumber: "",

        panNumber: "",

        email: "",

        phone: "",

        website: "",

        address: "",

        city: "",

        state: "",

        country: "",

        pincode: "",

        description: "",

        logoUrl: "",

        ownerId: ownerId

    });

    useEffect(() => {

        loadCompany();

    }, []);

    const loadCompany = async () => {

        try {

            const response =
                await companyService.getCompanyById(companyId);

            setCompany({

                companyName: response.companyName,

                gstNumber: response.gstNumber,

                panNumber: response.panNumber,

                email: response.email,

                phone: response.phone,

                website: response.website,

                address: response.address,

                city: response.city,

                state: response.state,

                country: response.country,

                pincode: response.pincode,

                description: response.description,

                logoUrl: response.logoUrl || "",

                ownerId:
                    response.owner?.id || ownerId

            });

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const handleChange = (e) => {

        setCompany({

            ...company,

            [e.target.name]:
                e.target.value

        });

    };

    const updateCompany = async (e) => {

        e.preventDefault();

        setSaving(true);

        try {

            const message =
                await companyService.updateCompany(

                    companyId,

                    company

                );

            alert(message);

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to update company."

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

                    Loading Company...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100 py-10">

            <div className="max-w-5xl mx-auto bg-white rounded-2xl shadow-lg p-8">

                <div className="flex justify-between items-center mb-10">

                    <div>

                        <h1 className="text-4xl font-bold">

                            Company Profile

                        </h1>

                        <p className="text-gray-500 mt-2">

                            Manage your company information

                        </p>

                    </div>

                    <span

                        className={`px-4 py-2 rounded-full text-white font-semibold ${
                            company.verified
                                ? "bg-green-600"
                                : "bg-red-600"
                        }`}

                    >

                        {

                            company.verified

                                ? "Verified"

                                : "Not Verified"

                        }

                    </span>

                </div>

                <form

                    onSubmit={updateCompany}

                    className="grid grid-cols-2 gap-6"

                >
                                <input
                                    type="text"
                                    name="companyName"
                                    placeholder="Company Name"
                                    value={company.companyName}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="email"
                                    name="email"
                                    placeholder="Company Email"
                                    value={company.email}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="gstNumber"
                                    placeholder="GST Number"
                                    value={company.gstNumber}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="panNumber"
                                    placeholder="PAN Number"
                                    value={company.panNumber}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="phone"
                                    placeholder="Phone Number"
                                    value={company.phone}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="website"
                                    placeholder="Website"
                                    value={company.website}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                />

                                <input
                                    type="text"
                                    name="city"
                                    placeholder="City"
                                    value={company.city}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="state"
                                    placeholder="State"
                                    value={company.state}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="country"
                                    placeholder="Country"
                                    value={company.country}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="pincode"
                                    placeholder="PIN Code"
                                    value={company.pincode}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3"
                                    required
                                />

                                <input
                                    type="text"
                                    name="logoUrl"
                                    placeholder="Company Logo URL"
                                    value={company.logoUrl}
                                    onChange={handleChange}
                                    className="border rounded-lg p-3 col-span-2"
                                />

                                <textarea
                                    name="address"
                                    placeholder="Company Address"
                                    value={company.address}
                                    onChange={handleChange}
                                    rows="3"
                                    className="border rounded-lg p-3 col-span-2"
                                    required
                                />

                                <textarea
                                    name="description"
                                    placeholder="Company Description"
                                    value={company.description}
                                    onChange={handleChange}
                                    rows="5"
                                    className="border rounded-lg p-3 col-span-2"
                                />

                                <button
                                    type="submit"
                                    disabled={saving}
                                    className="col-span-2 bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-lg text-lg font-semibold"
                                >
                                    {
                                        saving
                                            ? "Updating Company..."
                                            : "Update Company"
                                    }
                                </button>

                            </form>

                        </div>

                    </div>

                    );

                }

                export default CompanyProfile;