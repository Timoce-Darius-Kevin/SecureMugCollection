import { ListItem } from "../components/list_item";
import { useMugs, useDeleteMug } from "../features/mugs/hooks/use_mugs";
import { AddMugForm } from "../components/AddMugForm";
import type { Mug } from "../model/mug";

export function MugListPage() {
  const { data: mugs, error, isLoading } = useMugs();
  const deleteMug = useDeleteMug();
  console.log("Mugs data:", mugs);
  const handleDelete = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this mug?')) {
      await deleteMug.mutateAsync(id);
    }
  };

  return (
    <div className="container mx-auto p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">My Mugs</h1>
      </div>
      
      <AddMugForm />
      
      {isLoading && <p className="text-gray-600">Loading mugs...</p>}
      {error && <p className="text-red-500">Error loading mugs: {(error as Error).message}</p>}
      <div className="space-y-4">
        {mugs?.map((mug: Mug) => (
          <ListItem key={mug.id} mug={mug} onDelete={handleDelete} />
        ))}
        {mugs?.length === 0 && (
          <p className="text-gray-500 text-center py-8">
            No mugs found. Create your first mug using the button above!
          </p>
        )}
      </div>
    </div>
  );
}