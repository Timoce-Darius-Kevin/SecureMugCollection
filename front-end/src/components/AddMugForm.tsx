// components/AddMugForm.tsx
import { useState } from 'react';
import { useCreateMug } from '../features/mugs/hooks/use_mugs';

export function AddMugForm() {
  const [isOpen, setIsOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    material: '',
    description: '',
    diameter: 0
  });
  
  const createMug = useCreateMug();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createMug.mutateAsync(formData);
    setFormData({ name: '', material: '', description: '', diameter: 0 });
    setIsOpen(false);
  };

  return (
    <div className="mb-6">
      <button
        onClick={() => setIsOpen(true)}
        className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg"
      >
        + Add New Mug
      </button>

      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
          <div className="bg-white rounded-lg p-6 w-full max-w-md">
            <h2 className="text-xl font-bold mb-4">Add New Mug</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                type="text"
                placeholder="Mug Name"
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                className="w-full p-2 border rounded"
                required
              />
              <input
                type="text"
                placeholder="Material (e.g., Ceramic, Glass)"
                value={formData.material}
                onChange={(e) => setFormData({ ...formData, material: e.target.value })}
                className="w-full p-2 border rounded"
                required
              />
              <textarea
                placeholder="Description"
                value={formData.description}
                onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                className="w-full p-2 border rounded"
                required
              />
              <input
                type="number"
                step="0.1"
                placeholder="Diameter (cm)"
                value={formData.diameter}
                onChange={(e) => setFormData({ ...formData, diameter: parseFloat(e.target.value) })}
                className="w-full p-2 border rounded"
                required
              />
              <div className="flex justify-end space-x-2">
                <button
                  type="button"
                  onClick={() => setIsOpen(false)}
                  className="px-4 py-2 text-gray-600 hover:text-gray-800"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
                  disabled={createMug.isPending}
                >
                  {createMug.isPending ? 'Adding...' : 'Add Mug'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}