import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IClientNonPerson, defaultValue } from 'app/shared/model/client-non-person.model';

export const ACTION_TYPES = {
  FETCH_CLIENTNONPERSON_LIST: 'clientNonPerson/FETCH_CLIENTNONPERSON_LIST',
  FETCH_CLIENTNONPERSON: 'clientNonPerson/FETCH_CLIENTNONPERSON',
  CREATE_CLIENTNONPERSON: 'clientNonPerson/CREATE_CLIENTNONPERSON',
  UPDATE_CLIENTNONPERSON: 'clientNonPerson/UPDATE_CLIENTNONPERSON',
  DELETE_CLIENTNONPERSON: 'clientNonPerson/DELETE_CLIENTNONPERSON',
  RESET: 'clientNonPerson/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IClientNonPerson>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ClientNonPersonState = Readonly<typeof initialState>;

// Reducer

export default (state: ClientNonPersonState = initialState, action): ClientNonPersonState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CLIENTNONPERSON_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CLIENTNONPERSON):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CLIENTNONPERSON):
    case REQUEST(ACTION_TYPES.UPDATE_CLIENTNONPERSON):
    case REQUEST(ACTION_TYPES.DELETE_CLIENTNONPERSON):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CLIENTNONPERSON_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CLIENTNONPERSON):
    case FAILURE(ACTION_TYPES.CREATE_CLIENTNONPERSON):
    case FAILURE(ACTION_TYPES.UPDATE_CLIENTNONPERSON):
    case FAILURE(ACTION_TYPES.DELETE_CLIENTNONPERSON):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTNONPERSON_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTNONPERSON):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CLIENTNONPERSON):
    case SUCCESS(ACTION_TYPES.UPDATE_CLIENTNONPERSON):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CLIENTNONPERSON):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/client-non-people';

// Actions

export const getEntities: ICrudGetAllAction<IClientNonPerson> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CLIENTNONPERSON_LIST,
  payload: axios.get<IClientNonPerson>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IClientNonPerson> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CLIENTNONPERSON,
    payload: axios.get<IClientNonPerson>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IClientNonPerson> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CLIENTNONPERSON,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IClientNonPerson> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CLIENTNONPERSON,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IClientNonPerson> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CLIENTNONPERSON,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
