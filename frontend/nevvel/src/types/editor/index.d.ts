declare module "editor" {
    export interface episode {
        coverId: number;
        statusType: string;
        point: number;
        contents: content[];
    };

    interface content {
        idx: number;
        context: string;
        event: event[]
    }
    interface event {
        assetId: number;
        type: string
    }

}