import type { Mug } from "../model/mug";

type ListItemProps = {
    mug: Mug;
    onDelete?: (id: number) => void;
};

export function ListItem({ mug, onDelete }: ListItemProps) {
    return (
        <div className="rounded-2xl bg-gradient-to-r from-blue-500 via-purple-700 to-purple-500 p-5 my-3 shadow-md text-white flex flex-col min-w-0 transition-transform cursor-pointer hover:scale-[1.02]">
            <div className="flex justify-between items-start">
                <div>
                    <span className="font-bold text-lg mb-1 drop-shadow-sm">
                        {mug.name}
                    </span>
                    <span className="font-normal text-base opacity-90 drop-shadow block">
                        {mug.description}
                    </span>
                    <span className="font-normal text-sm opacity-80 drop-shadow block mt-2">
                        Material: {mug.material} | Diameter: {mug.diameter}cm
                    </span>
                </div>
                {onDelete && (
                    <button
                        onClick={() => onDelete(mug.id)}
                        className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded text-sm"
                    >
                        Delete
                    </button>
                )}
            </div>
        </div>
    );
}